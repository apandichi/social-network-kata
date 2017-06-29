import spock.lang.Specification


class AggregatedListOfTimelinesTest extends Specification {

	def alice = new SocialNetworkUser()
	def bob = new SocialNetworkUser()
	def charlie = new SocialNetworkUser()

	def message(text) {
		return new Message(text: text)
	}

	def "Charlie can view an aggregated list of Alice's and Bob's timelines"() {
		given: "Alice's messages"
		def messageOne = message("message 1")
		def messageTwo = message("message 2")
		def alicesMessages = [messageOne, messageTwo]

		and: "Bob's messages"
		def messageThree = message("message 3")
		def bobsMessages = [messageThree]

		and: "Charlie follows Alice and Bob"
		charlie.follow(alice)
		charlie.follow(bob)

		when: "Alice publishes two messages"
		alice.publish(messageOne)
		alice.publish(messageTwo)

		and: "Bob publishes one message"
		bob.publish(messageThree)

		then: "Charlie's newsfeed contains all messages"
		charlie.newsfeed().containsAll(alicesMessages + bobsMessages)
	}
}