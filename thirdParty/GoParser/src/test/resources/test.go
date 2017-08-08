package acache

func NewAppengineThrottler(c appengine.Context) AppengineThrottler {
	return AppengineThrottler{c}
}
