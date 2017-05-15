import spock.lang.Specification


class SocialNetworkTests extends Specification {

	def "Alice has an empty personal timeline"() {
		given: "A user named Alice"

		when: "Alice inspects her personal timeline"
		def alicesPersonalTimeline = alicesPersonalTimeline()

		then: "Alice sees an empty personal timeline"
		alicesPersonalTimeline == emptyPersonalTimeline()
	}

	def alicesPersonalTimeline() {
		true
	}

	def emptyPersonalTimeline() {
		true
	}
}