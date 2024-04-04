package task1;

import java.util.List;

public class Laureate {

    private String name;
    private String birth;
    private String death;
    private String nations;
    private List<String> languages;
    private List<String> genres;
    private String citation;

    public Laureate(String name, String birth, String death, String nations, List<String> languages, List<String> genres, String citation) {
        this.name = name;
        this.birth = birth;
        this.death = death;
        this.nations = nations;
        this.languages = languages;
        this.genres = genres;
        this.citation = citation;
    }

    public String getName() {
        return name;
    }

    public String getBirth() {
        return birth;
    }

    public String getDeath() {
        return death;
    }
    
    public String getNations() {
        return nations;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getCitation() {
        return citation;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nName: ").append(name);
        sb.append("\nBirth: ").append(birth);
        sb.append("\nDeath: ").append(death);
        sb.append("\nNations: ").append(nations);
        sb.append("\nLanguages: ").append(languages);
        sb.append("\nGenres: ").append(genres);
        sb.append("\nCitation: ").append(citation);
        sb.append("\n");
        return sb.toString();
    }
}
