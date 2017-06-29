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
		given: "A user named Alice and her messages"
		def alicesMessages = [
				message("Hello, this is my first message"), message("Is this thing on?")]

		when: "Alice publishes her messages to her timeline"
		alicesMessages.each {
			userPublishesMessageToTheirTimeline(alice, it)
		}

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
		given:
		charlie.follow(alice)
		charlie.follow(bob)
		def alicesMessages = userPublishesMessages(alice, [message("message 1"), message("message 2")])
		def bobsMessages = userPublishesMessages(bob, [message("message 3")])

		when:
		def newsfeed = charlie.newsfeed()

		then:
		newsfeed.containsAll(alicesMessages + bobsMessages)
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