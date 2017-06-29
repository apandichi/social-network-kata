import spock.lang.Specification


class SocialNetworkTests extends Specification implements MessageBuilderTrait {

	def alice = new SocialNetworkUser()
	def bob = new SocialNetworkUser()
	def charlie = new SocialNetworkUser()

	def "Alice has an empty timeline"() {
		expect:
		alice.timeline().isEmpty()
	}

	def "Alice can publish a message to her timeline"() {
		given: "A user named Alice and her message"
		def alicesMessage = message("Hello, this is my first message")

		when: "Alice publishes her message to her timeline"
		alice.publish(alicesMessage)

		then: "Alices's timeline contains her message"
		alice.timeline().contains(alicesMessage)
	}

	def "Alice can publish multiple messages to her timeline"() {
		given: "Alice's messages"
		def messageOne = message("Hello, this is my first message")
		def messageTwo = message("Is this thing on?")
		def alicesMessages = [messageOne, messageTwo]

		when: "Alice publishes her messages to her timeline"
		alice.publish(messageOne)
		alice.publish(messageTwo)

		then: "Alices's timeline contains all her messages"
		alice.timeline().containsAll(alicesMessages)
	}

	def "Charile can subscribe to Alice's and Bob's timelines"() {
		when: "Charlie follows Alice and Bob"
		charlie.follow(alice)
		charlie.follow(bob)

		then: "Alice and Bob are in Charlie's list of people subscribed to"
		charlie.followings().contains(alice)
		charlie.followings().contains(bob)
	}
}