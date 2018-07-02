package take.home.cook.api.domain;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table("book")
public class    Book {
    @PrimaryKeyColumn(
            name = "isbn",
            ordinal = 2,
            type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private UUID id;
    @PrimaryKeyColumn(
            name = "title", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @Indexed
    private String title;
    @PrimaryKeyColumn(
            name = "publisher", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    @Indexed
    private String publisher;
    @Column
    private Set<String> tags = new HashSet<>();


    public Book(UUID id, String title, String publisher, Set<String> tags) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.tags = tags;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", tags=" + tags +
                '}';
    }
}