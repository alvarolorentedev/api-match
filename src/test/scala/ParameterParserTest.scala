import java.util.UUID

import org.scalatest.{FlatSpec, Matchers}


class ParamenterParserTest extends FlatSpec with Matchers {
  it should "parse parameters correctly" in {
    val main = UUID.randomUUID().toString
    val mainPath = UUID.randomUUID().toString
    val matching = UUID.randomUUID().toString
    val matchingPath = UUID.randomUUID().toString
    val parameters = Array[String]("--main", main, "--mainpath", mainPath, "--matching", matching, "--matchingpath", matchingPath)
    val result = new ParameterParser(parameters)
    result.main() should equal (main)
    result.mainpath() should equal (mainPath)
    result.matching() should equal (matching)
    result.matchingpath() should equal (matchingPath)
  }
}
