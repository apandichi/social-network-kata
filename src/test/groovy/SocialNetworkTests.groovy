import spock.lang.Specification


class SocialNetworkTests extends Specification {

	def alice = new SocialNetworkUser()

	class SocialNetworkUser {
		def timeline = []

		def timeline() {
			timeline
		}

		def publishToTimeline(message) {
			timeline.add(message)
		}
	}

	def "Alice has an empty timeline"() {
		when: "Alice inspects her timeline"
		def alicesTimeline = timelineOf(alice)

		then: "Alice sees an empty timeline"
		alicesTimeline.isEmpty()
	}

	def "Alice can publish a message to her timeline"() {
		given: "A user named Alice and her message"
		def alicesMessage = "Hello, this is my first message"

		when: "Alice publishes her message to her timeline"
		userPublishesMessageToTheirTimeline(alice, alicesMessage)

		then: "Alices's timeline contains her message"
		timelineOf(alice).contains(alicesMessage)
	}

	def "Alice can publish multiple messages to her timeline"() {
		given: "A user named Alice and her messages"
		def alicesMessages = ["Hello, this is my first message", "Is this thing on?"]

		when: "Alice publishes her messages to her timeline"
		userPublishesMessageToTheirTimeline(alice, alicesMessages[0])
		userPublishesMessageToTheirTimeline(alice, alicesMessages[1])

		then: "Alices's timeline contains all her messages"
		timelineOf(alice).containsAll(alicesMessages)
	}

	def userPublishesMessageToTheirTimeline(alice, alicesMessage) {
		alice.publishToTimeline(alicesMessage)
	}

	def timelineOf(user) {
		user.timeline()
	}

}