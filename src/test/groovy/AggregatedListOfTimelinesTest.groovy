import spock.lang.Specification


class AggregatedListOfTimelinesTest extends Specification implements MessageBuilderTrait {

	// social network users
	def alice = new SocialNetworkUser()
	def bob = new SocialNetworkUser()
	def charlie = new SocialNetworkUser()

	// Alice's messages
	def messageOne = message("message 1")
	def messageTwo = message("message 2")
	def alicesMessages = [messageOne, messageTwo]

	// Bob's messages
	def messageThree = message("message 3")
	def bobsMessages = [messageThree]

	def "Charlie can view an aggregated list of Alice's and Bob's timelines"() {
		given: "Charlie follows Alice and Bob"
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