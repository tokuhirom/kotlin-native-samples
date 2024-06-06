import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.required

fun main(args: Array<String>) {
    val parser = ArgParser("example")
    val input by parser.option(ArgType.String, shortName = "i", description = "Input file")
        .required()
    parser.parse(args)
    println("Input file: $input")
}
