import spock.lang.Specification


class SocialNetworkTests extends Specification {

	def "Alice has an empty personal timeline"() {
		given: "A user named Alice"
		def alice

		when: "Alice inspects her personal timeline"
		def alicesPersonalTimeline = personalTimelineOf(alice)

		then: "Alice sees an empty personal timeline"
		alicesPersonalTimeline == emptyPersonalTimeline()
	}

	def personalTimelineOf(user) {
		true
	}

	def emptyPersonalTimeline() {
		true
	}
}