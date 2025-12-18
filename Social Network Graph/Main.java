import java.util.*;

class User {
    int id;
    String name;

    User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getID() {
        return this.id;
    }
}

class SocialNetworkGraph {
    private Map<User, Set<User>> adjList = new HashMap<>();

    public void addUser(User user) {
        adjList.putIfAbsent(user, new HashSet<>());
    }

    public void addFriends(User u1, User u2) {
        addUser(u1);
        addUser(u2);
        if (u1.equals(u2))
            return;
        adjList.get(u1).add(u2);
        adjList.get(u2).add(u1);
    }

    public List<User> getFriends(User user) {
        Set<User> friends = adjList.get(user);
        if (friends == null)
            return new ArrayList<>();
        return new ArrayList<>(friends);
    }

    public List<User> recommendFriends(User startUser) {
        if (!adjList.containsKey(startUser))
            return new ArrayList<>();

        Set<User> visited = new HashSet<>();
        Queue<User> queue = new LinkedList<>();
        Set<User> direct = adjList.get(startUser);
        Set<User> recommendations = new HashSet<>();

        queue.add(startUser);
        visited.add(startUser);

        int distance = 0;
        while (!queue.isEmpty() && distance < 2) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                User current = queue.poll();
                Set<User> neighbors = adjList.getOrDefault(current, Collections.emptySet());

                for (User neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);

                        if (!neighbor.equals(startUser) && !direct.contains(neighbor)) {
                            recommendations.add(neighbor);
                        }
                    }
                }
            }
            distance++;
        }
        return new ArrayList<>(recommendations);
    }
}

public class Main {
    public static void main(String[] args) {
        SocialNetworkGraph graph = new SocialNetworkGraph();

        User rahul = new User(1, "Rahul");
        User tushar = new User(2, "Tushar");
        User yash = new User(3, "Yash");
        User ankesh = new User(4, "Ankesh");
        User shiva = new User(5, "Shiva");

        graph.addUser(rahul);
        graph.addUser(tushar);
        graph.addUser(yash);
        graph.addUser(ankesh);
        graph.addUser(shiva);

        graph.addFriends(rahul, tushar);
        graph.addFriends(rahul, ankesh);
        graph.addFriends(rahul, shiva);

        graph.addFriends(tushar, yash);
        graph.addFriends(tushar, rahul);
        graph.addFriends(tushar, shiva);

        System.out.println("Rahul's friends:");
        for (User u : graph.getFriends(rahul)) {
            System.out.println(u.name);
        }

        System.out.println("Rahul's recommendation:");
        for (User u : graph.recommendFriends(rahul)) {
            System.out.println(u.name);
        }

        System.out.println(rahul.getID());
    }
}