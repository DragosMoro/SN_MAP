package repository;

import domain.Friendship;
import domain.User;
import exceptions.NetworkException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class UserNetwork implements Network<User>{
    private final Map<Integer, Vector<Integer>> network = new HashMap<>();

    public UserNetwork() {
    }
    public void addToNetwork(User entity)
    {
        network.put(entity.getId(),new Vector<>());
    }
    public void removeFromNetwork(User entity)
    {
        for(Vector<Integer> networkEntitys : network.values() )
            networkEntitys.remove(entity.getId());
        network.remove(entity.getId());

    }
    private Boolean friendshipExists(int id1, int id2)
    {

        Vector<Integer> friends = network.get(id1);
        for(int friendsId : friends)
        {
            if (friendsId == id2)
                return true;
        }
        return false;
    }
    public void addFriendship(Friendship friendship) throws NetworkException
    {
        if (friendshipExists(friendship.getFirstUserID(), friendship.getSecondUserID())) {
            throw new NetworkException("There is already a friendship.\n");
        }
        network.get(friendship.getFirstUserID()).add(friendship.getSecondUserID());
        network.get(friendship.getSecondUserID()).add(friendship.getFirstUserID());

        }

    public void removeFriendship(Friendship friendship) throws NetworkException
    {
        if (!friendshipExists(friendship.getFirstUserID(), friendship.getSecondUserID())) {
            throw new NetworkException("There is no friendship between these 2 people.\n");
        }
        network.get(friendship.getFirstUserID()).removeElement(friendship.getSecondUserID());
        network.get(friendship.getSecondUserID()).removeElement(friendship.getFirstUserID());
    }
    private int dfs(int[] users, int id, int communityNumber) {
        users[Math.toIntExact(id)] = communityNumber;
        int numberOfPath = 0;
        Vector<Integer> friends = network.get(id);
        for (int idFriend : friends) {
            if (users[Math.toIntExact(idFriend)] == 0) {
                int path = dfs(users, idFriend, communityNumber);
                numberOfPath = Math.max(numberOfPath, path);
            }
        }
        return numberOfPath + 1;
    }

    private int getMaxId() {
        int max = 0;
        for (int id : network.keySet()) {
            max = Math.max(max, id);
        }
        return max;
    }

    private int[] getCommunities() {
        int[] users = new int[(int) (getMaxId() + 1)];
        int communityNumber = 1;
        for (int id : network.keySet()) {
            if (users[Math.toIntExact(id)] == 0) {
                dfs(users, id, communityNumber);
                communityNumber = communityNumber + 1;
            }
        }
        return users;
    }

    private Map<Integer, Integer> getPopulation() {
        int[] users = getCommunities();
        Map<Integer, Integer> communitiesPopulation = new HashMap<>();

        for (int count = 1; count < users.length; count = count + 1) {
            if (users[count] == 0) {
                continue;
            }

            if (!communitiesPopulation.containsKey(users[count])) {
                communitiesPopulation.put(users[count], 0);
            }
            communitiesPopulation.put(users[count], communitiesPopulation.get(users[count]) + 1);
        }

        return communitiesPopulation;
    }
    public int getNumberOfComunities()
    {
        Map<Integer, Integer> communitiesPopulation = getPopulation();

        return communitiesPopulation.size();
    }
    public Vector<Integer> mostSociableCommunity()
    {
        int[] users = new int[(int) (getMaxId() + 1)];
        Vector<Integer> biggestCommunity = new Vector<>();
        int communityNumber = 1;
        int maxPath = 0;
        int currentPath;
        int startNode = -1;
        for (int id : network.keySet()) {
            if (users[Math.toIntExact(id)] == 0) {
                currentPath = dfs(users, id, communityNumber);
                communityNumber = communityNumber + 1;
                if (currentPath > maxPath) {
                    maxPath = currentPath;
                    startNode = id;
                }
            }
        }
        Arrays.fill(users, 0);
        dfs(users, startNode, 1);

        for (int count = 1; count < users.length; count = count + 1) {
            if (users[count] != 1) {
                continue;
            }
            biggestCommunity.add((int) count);
        }

        return biggestCommunity;
    }
}
