import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        JDBCAuthorDAO author = new JDBCAuthorDAO(connectionFactory);
        JDBCArticleDAO article = new JDBCArticleDAO(connectionFactory);

        author.insert(new Author( "Denis Gurov"));
        author.insert(new Author("Mikhail Kolomietc"));
        author.insert(new Author("Ivan Visloguzov"));

        Author last = null;
        System.out.println("1 -------------------------");
        for (Author auth : author.getAll()) {
            System.out.println(author);
            last = auth;
        }

        System.out.println("2 -------------------------");
        author.update(last);
        author.getAll().forEach(System.out::println);

        System.out.println("3 -------------------------");
        System.out.println(author.getById(last.getId()));

        System.out.println("4 -------------------------");
        author.deleteById(last.getId());
        author.getAll().forEach(System.out::println);

        System.out.println("5 -------------------------");
        author.deleteAll();
        author.getAll().forEach(System.out::println);

    }
}
