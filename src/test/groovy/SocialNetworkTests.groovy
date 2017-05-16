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
		expect:
		alice.timeline().isEmpty()
	}

	def "Alice can publish a message to her timeline"() {
		given: "A user named Alice and her message"
		def alicesMessage = "Hello, this is my first message"

		when: "Alice publishes her message to her timeline"
		userPublishesMessageToTheirTimeline(alice, alicesMessage)

		then: "Alices's timeline contains her message"
		alice.timeline().contains(alicesMessage)
	}

	def "Alice can publish multiple messages to her timeline"() {
		given: "A user named Alice and her messages"
		def alicesMessages = ["Hello, this is my first message", "Is this thing on?"]

		when: "Alice publishes her messages to her timeline"
		alicesMessages.each {
			userPublishesMessageToTheirTimeline(alice, it)
		}

		then: "Alices's timeline contains all her messages"
		alice.timeline().containsAll(alicesMessages)
	}

	def userPublishesMessageToTheirTimeline(alice, alicesMessage) {
		alice.publishToTimeline(alicesMessage)
	}
}