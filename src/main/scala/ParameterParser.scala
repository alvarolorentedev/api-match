import org.rogach.scallop.ScallopConf

class ParameterParser (arguments: Seq[String]) extends ScallopConf(arguments) {
    val main = opt[String](required = true)
    val mainpath = opt[String](required = true)
    val matching = opt[String](required = true)
    val matchingpath = opt[String](required = true)
    verify()
}
