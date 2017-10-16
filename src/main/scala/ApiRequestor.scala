trait ApiData{
  def uri: String
  def path: String
}

class ApiDataImpl(uriParam: String, pathParam: String) extends ApiData{
  override def uri: String = uriParam
  override def path: String = pathParam
}

class ApiRequestor(arguments: ApiData) {

}
