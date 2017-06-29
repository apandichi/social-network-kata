import spock.lang.Specification


class SocialNetworkTests extends Specification {

	def alice = new SocialNetworkUser()
	def bob = new SocialNetworkUser()
	def charlie = new SocialNetworkUser()

	class SocialNetworkUser {
		def timeline = []
		def following = []

		def timeline() {
			timeline
		}

		def publish(message) {
			timeline.add(message)
		}

		def followings() {
			following
		}

		def follow(def user) {
			following.add(user)
		}

		def newsfeed() {
			followings().inject([], {newsfeed, user ->
				newsfeed.addAll(user.timeline())
				return newsfeed
			})
		}
	}

	class Message {
		def text
	}

	def "Alice has an empty timeline"() {
		expect:
		alice.timeline().isEmpty()
	}

	def message(text) {
		return new Message(text: text)
	}

	def "Alice can publish a message to her timeline"() {
		given: "A user named Alice and her message"
		def alicesMessage = message("Hello, this is my first message")

		when: "Alice publishes her message to her timeline"
		userPublishesMessageToTheirTimeline(alice, alicesMessage)

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

	def userPublishesMessages(def user, def messages) {
		messages.each {
			userPublishesMessageToTheirTimeline(user, it)
		}
	}

	def userPublishesMessageToTheirTimeline(alice, alicesMessage) {
		alice.publish(alicesMessage)
	}
}