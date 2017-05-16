import spock.lang.Specification


class SocialNetworkTests extends Specification {

	def alice

	def "Alice has an empty timeline"() {
		when: "Alice inspects her timeline"
		def alicesTimeline = timelineOf(alice)

		then: "Alice sees an empty timeline"
		alicesTimeline == emptyTimeline()
	}

	def "Alice can publish a message to her timeline"() {
		given: "A user named Alice and her message"
		def alicesMessage = "Hello, this is my first message"

		when: "Alice publishes her message to her timeline"
		userPublishesMessageToTheirTimeline(alice, alicesMessage)

		then: "Alices's timeline contains her message"
		timelineOf(alice) == timelineContaining(alicesMessage)
	}

	def "Alice can publish multiple messages to her timeline"() {
		given: "A user named Alice and her messages"
		def alicesMessages

		expect: "Alices's timeline contains all her messages"
		timelineOf(alice) == timelineContaining(alicesMessages)
	}

	def userPublishesMessageToTheirTimeline(alice, alicesMessage) {
	}

	def timelineContaining(message) {
		true
	}

	def timelineOf(user) {
		true
	}

	def emptyTimeline() {
		true
	}
}