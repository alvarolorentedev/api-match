import java.util.UUID


object Faker {
  def RandomString : String = { UUID.randomUUID().toString }
  def RandomUrl : String = { "http://" + UUID.randomUUID().toString + ".com" }

}
