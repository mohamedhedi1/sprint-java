/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectfx.services;

/**
 *
 * @author manar
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import sportconnectfx.entities.Blog;

public class BlogStatistics {

    private final List<Blog> blogs;

    public BlogStatistics(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public ObservableList<PieChart.Data> getBlogStatsByAuthor() {
        // Calcul de la répartition des blogs publiés par auteur
Map<String, Long> blogStats = blogs.stream().collect(Collectors.groupingBy(blog -> blog.getAuteur(), Collectors.counting()));

        // Conversion du résultat en une liste observable de données pour le graphique PieChart
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        blogStats.forEach((author, count) -> data.add(new PieChart.Data(author, count)));

        return data;
    }

    public ObservableList<PieChart.Data> getBlogLikesDislikesStats() {
        // Calcul de la répartition des likes et dislikes pour chaque blog
        List<PieChart.Data> blogStats = blogs.stream()
                .map(blog -> new PieChart.Data(blog.getTitre() + " Likes", blog.getLikes()))
                .collect(Collectors.toList());

        // Ajout des dislikes à la liste de données pour le graphique PieChart
        blogs.stream().map(blog -> new PieChart.Data(blog.getTitre() + " Dislikes", blog.getDislikes()))
                .forEach(blogStats::add);

        return FXCollections.observableArrayList(blogStats);
    }
}
