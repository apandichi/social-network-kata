trait MessageBuilderTrait {
	def message(text) {
		return new Message(text: text)
	}
}