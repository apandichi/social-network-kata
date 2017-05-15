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
		given: "A user named Alice"
		def alice

		expect:
		timelineOf(alice) == timelineContainingAlicesMessage()
	}

	def timelineContainingAlicesMessage() {
		
	}

	def timelineOf(user) {
		true
	}

	def emptyTimeline() {
		true
	}
}