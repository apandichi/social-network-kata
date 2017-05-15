import spock.lang.Specification


class SocialNetworkTests extends Specification {

	def "Alice has an empty personal timeline"() {
		expect:
		alicesPersonalTimeline() == emptyPersonalTimeline()
	}

	def alicesPersonalTimeline() {
		true
	}

	def emptyPersonalTimeline() {
		true
	}
}