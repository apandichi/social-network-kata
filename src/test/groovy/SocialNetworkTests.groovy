import spock.lang.Specification


class SocialNetworkTests extends Specification {

	def "Alice has an empty timeline"() {
		given: "A user named Alice"
		def alice

		when: "Alice inspects her timeline"
		def alicesTimeline = timelineOf(alice)

		then: "Alice sees an empty timeline"
		alicesTimeline == emptyTimeline()
	}

	def "Alice can publish a message to her timeline"() {
		given: "A user named Alice and her message"
		def alice
		def alicesMessage

		when: "Alice publishes her message to her timeline"
		alicePublishesMessageToHerTimeline()

		then: "Alices's timeline contains her message"
		timelineOf(alice) == timelineContaining(alicesMessage)
	}

	def alicePublishesMessageToHerTimeline() {
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