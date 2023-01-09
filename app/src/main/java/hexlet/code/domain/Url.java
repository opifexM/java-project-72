package hexlet.code.domain;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Entity
public class Url extends Model {
    @Id
    private long id;

    private String name;

    @WhenCreated
    private Instant createdAt;

    // один сайт к многим отчетам
    @OneToMany
    private List<UrlCheck> urlChecks;

    public Url(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<UrlCheck> getUrlChecks() {
        return urlChecks;
    }

    public Instant getLastCreatedAt() {
        if (urlChecks.isEmpty()) {
            return null;
        }
        if (urlChecks.size() == 1) {
            return urlChecks.get(0).getCreatedAt();
        }
        return urlChecks.get(urlChecks.size()-1).getCreatedAt();
    }

    public int getLastStatusCode() {
        if (urlChecks.isEmpty()) {
            return 0;
        }
        if (urlChecks.size() == 1) {
            return urlChecks.get(0).getStatusCode();
        }
        return urlChecks.get(urlChecks.size()-1).getStatusCode();
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
