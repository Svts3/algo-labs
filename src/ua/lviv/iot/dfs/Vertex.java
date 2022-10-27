package ua.lviv.iot.dfs;


import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class Vertex {

    int experience;
    public Vertex right;
    public Vertex left;

    public Vertex(int experience) {
        this.experience = experience;
    }




}
