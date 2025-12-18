# Social Network Graph (Java)

                               Rahul
                              /  |  \                         
                             /   |   \  
                       Tushar  Shiva  Ankesh
                      /
                     /
                   Yash  
## Overview
This project demonstrates a simple social graph implementation with:
- A User model (identified by id and name).
- A SocialNetworkGraph that stores friendships and provides friend recommendations.

## Key classes & functions

- User
  - Fields: id (int), name (String)
  - Constructor: User(int id, String name)
  - equals / hashCode: Users are equal based on `id` (so id uniquely identifies a user).
  - getID(): returns the user's id.

- SocialNetworkGraph
  - addUser(User user): Ensure a user node exists in the graph.
  - addFriends(User u1, User u2): Create a bidirectional friendship (undirected edge). No-op if users are the same.
  - getFriends(User user): Returns a List of direct friends (neighbors) or an empty list if none.
  - recommendFriends(User startUser): Returns users at distance 2 from startUser (friends-of-friends) excluding direct friends and the user themself.
    - Implemented with a BFS limited to depth 2.
    - Uses a Set to avoid duplicates and preserves correctness even if graph has cycles.

## Algorithm notes
- recommendFriends performs a breadth-first search starting from `startUser`, exploring neighbors up to distance 2.
- Complexity: O(V + E) in the worst case for the BFS traversal; since depth is limited to 2 it is typically much smaller.

## Usage
- Compile and run `Main.java`. The `main` function in `Main` shows example usage:
  - Create users, add them to the graph, add friendships, print friends and recommendations.

## Example output (from provided main)
Rahul's friends:
- Tushar
- Ankesh
- Shiva

Rahul's recommendation:
- Yash

(Plus Rahul's id printed at the end)
