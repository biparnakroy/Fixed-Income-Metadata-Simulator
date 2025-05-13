
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class FixedIncomeProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        String topic = "fixed-income-securities";

        for (int i = 1; i <= 10; i++) {
            String json = String.format(
                "{\"id\":\"SEC-%d\",\"issuer\":\"Corp %d\",\"maturity\":\"2030-01-01\",\"coupon\":5.0}",
                i, i
            );
            producer.send(new ProducerRecord<>(topic, "SEC-" + i, json));
        }

        producer.close();
    }
}
