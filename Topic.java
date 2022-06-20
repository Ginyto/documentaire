public class Topic {

    private int TopicId;
    private String Topic;

    public Topic(int TopicId, String Topic) {
        this.TopicId = TopicId;
        this.Topic = Topic;
    }

    public int getTopicId() {
        return TopicId;
    }

    public void setTopicId(int TopicId) {
        this.TopicId = TopicId;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String Topic) {
        this.Topic = Topic;
    }

    @Override
    public String toString() {
        return "Topic{" + "TopicId=" + TopicId + ", Topic=" + Topic + '}';
    }
}
