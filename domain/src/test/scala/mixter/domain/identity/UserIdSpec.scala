package mixter.domain.identity

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class UserIdSpec extends AnyWordSpec with Matchers {
  val AN_EMAIL = "mail@mix-it.fr"
  "a new UserId" should {
    "be created with an email" in {
      val expectedUserId = UserId(AN_EMAIL)
      val actualUserId = UserId.of(AN_EMAIL)
      actualUserId shouldBe Some(expectedUserId)
    }
    "not be created from an empty email" in {
      val expectedUserId = None
      val actualUserId = UserId.of("")
      actualUserId shouldBe expectedUserId
    }
    "not be created from a null email" in {
      val expectedUserId = None
      val actualUserId = UserId.of(null)
      actualUserId shouldBe expectedUserId
    }
  }
}
