import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.option

class Tool : CliktCommand() {
    val opt: String? by option(help = "an option")
    val arg: String by argument(help = "an argument")

    override fun run() {
        println("opt=$opt, arg=$arg")
    }
}

fun main(args: Array<String>) {
    Tool().main(args)
}
