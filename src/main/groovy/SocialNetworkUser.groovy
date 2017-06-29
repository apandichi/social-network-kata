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