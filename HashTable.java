public class HashTable 
{
    int size;
    Node table[];
    
    // Node class for linked list implementation
    public static class Node 
    {
        int key;
        String value;
        Node next;
        
        Node(int key, String value) 
        {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    
    public HashTable() 
    {
        this(10);
    }
    
    public HashTable(int size) 
    {
        this.size = size;
        this.table = new Node[size];
    }
    
    public int hashFunction(int key) 
    {
        return key % size;
    }
    
    public void insert(int key, String value) 
    {
        int index = hashFunction(key);
        Node current = table[index];
        Node prev = null;
        
        // Check if key already exists and update
        while (current != null) 
        {
            if (current.key == key) 
            {
                current.value = value;
                System.out.println("Updated key " + key + " with value " + value);
                return;
            }
            prev = current;
            current = current.next;
        }
        
        // Else insert new key-value pair at beginning of chain
        Node newNode = new Node(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        System.out.println("Inserted key " + key + " with value " + value);
    }
    
    public String search(int key) 
    {
        int index = hashFunction(key);
        Node current = table[index];
        
        while (current != null) 
        {
            if (current.key == key) 
            {
                return current.value;
            }
            current = current.next;
        }
        return null;  // Not found
    }
    
    public void delete(int key) 
    {
        int index = hashFunction(key);
        Node current = table[index];
        Node prev = null;
        
        while (current != null) 
        {
            if (current.key == key) 
            {
                if (prev == null) 
                {
                    // Delete first node in chain
                    table[index] = current.next;
                } 
                else 
                {
                    // Delete middle or last node
                    prev.next = current.next;
                }
                System.out.println("Deleted key " + key);
                return;
            }
            prev = current;
            current = current.next;
        }
        System.out.println("Key " + key + " not found for deletion.");
    }
    
    public void display() 
    {
        System.out.println("Hash Table:");
        for (int i = 0; i < table.length; i++) 
        {
            System.out.print("Index " + i + ": ");
            Node current = table[i];
            while (current != null) 
            {
                System.out.print("[" + current.key + ", " + current.value + "]");
                if (current.next != null) 
                {
                    System.out.print(" -> ");
                }
                current = current.next;
            }
            System.out.println();
        }
    }
    
    // Main method to test the implementation
    public static void main(String[] args) 
    {
        HashTable ht = new HashTable();
        
        ht.insert(15, "apple");
        ht.insert(25, "banana");  // Collision with 15
        ht.insert(35, "cherry");  // Collision again
        
        System.out.println("Search 25: " + ht.search(25));  // Output: banana
        ht.delete(25);
        System.out.println("Search 25 after deletion: " + ht.search(25));  // Output: null
        
        ht.display();
    }
}