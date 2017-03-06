package mixter.domain.identity.handlers

import java.time.LocalDateTime
import mixter.domain.identity._
import mixter.domain.identity.event.{UserConnected, UserDisconnected}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class RegisterSessionSpec extends AnyWordSpec with Matchers with WithSessionRepository{

  "A RegisterSession handler" should {
    "save a connected SessionProjection when it receives UserConnected event" in withSessionRepository { sessionRepository =>
      // Given
      val userConnected = UserConnected(SessionId(), UserId("user@mixit.fr"), LocalDateTime.now())
      val handler = new RegisterSession(sessionRepository)
      // When
      handler.apply(userConnected)
      // Then
      val sessionProjection = SessionProjection(userConnected.sessionId, userConnected.userId, SessionStatus.CONNECTED)
      sessionRepository.getSessions should contain only(sessionProjection)
    }

    "save a disconnected SessionProjection when it receives UserDisconnected event" in withSessionRepository { sessionRepository =>
      // Given
      val userDisconnected = UserDisconnected(SessionId(), UserId("user@mixit.fr"))
      val handler = new RegisterSession(sessionRepository)
      // When
      handler.apply(userDisconnected)
      // Then
      val sessionProjection = SessionProjection(userDisconnected.sessionId, userDisconnected.userId, SessionStatus.DISCONNECTED)
      sessionRepository.getSessions should contain only(sessionProjection)
    }
  }

}
