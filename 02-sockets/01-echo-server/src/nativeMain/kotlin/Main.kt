import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.refTo
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.sizeOf
import kotlinx.cinterop.value
import platform.posix.AF_INET
import platform.posix.INADDR_ANY
import platform.posix.SOCK_STREAM
import platform.posix.accept
import platform.posix.bind
import platform.posix.close
import platform.posix.listen
import platform.posix.memset
import platform.posix.perror
import platform.posix.posix_htons
import platform.posix.recv
import platform.posix.send
import platform.posix.sockaddr_in
import platform.posix.socket
import platform.posix.socklen_tVar

@OptIn(ExperimentalForeignApi::class)
fun main() {
    val serverSocket = socket(AF_INET, SOCK_STREAM, 0)
    if (serverSocket == -1) {
        perror("socket")
        return
    }

    val port: Short = 12345

    memScoped {
        val serverAddr = alloc<sockaddr_in>()
        memset(serverAddr.ptr, 0, sizeOf<sockaddr_in>().toULong())
        serverAddr.sin_family = AF_INET.convert()
        serverAddr.sin_port = posix_htons(port).toUShort()
        serverAddr.sin_addr.s_addr = INADDR_ANY

        if (bind(serverSocket, serverAddr.ptr.reinterpret(), sizeOf<sockaddr_in>().convert()) == -1) {
            perror("bind")
            return
        }

        if (listen(serverSocket, 10) == -1) {
            perror("listen")
            return
        }

        println("Server is listening on port 12345")

        while (true) {
            val clientAddr = alloc<sockaddr_in>()
            val clientAddrLen = alloc<socklen_tVar>()
            clientAddrLen.value = sizeOf<sockaddr_in>().convert()

            val clientSocket = accept(serverSocket, clientAddr.ptr.reinterpret(), clientAddrLen.ptr)
            if (clientSocket == -1) {
                perror("accept")
                continue
            }

            println("Client connected")

            val buffer = ByteArray(1024)
            while (true) {
                val bytesRead = recv(clientSocket, buffer.refTo(0), buffer.size.convert(), 0)
                if (bytesRead <= 0) {
                    if (bytesRead.toInt() == 0) {
                        println("Client disconnected")
                    } else {
                        perror("recv")
                    }
                    break
                }
                send(clientSocket, buffer.refTo(0), bytesRead.convert(), 0)
            }

            close(clientSocket)
        }
    }

    close(serverSocket)
}
