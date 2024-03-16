package final_attestation;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        PriorityQueue<Product> toysList = put();
        write(toysList);
        System.out.println(listID(toysList));
        System.out.println(listName(toysList));
        System.out.println(listWeight(toysList));

        int countLot = toysList.size();

        System.out.println(queue(toysList));
        get(queue(toysList), countLot);
        get(queue(toysList), countLot);
        get(queue(toysList), countLot);
        get(queue(toysList), countLot);
        get(queue(toysList), countLot);
        get(queue(toysList), countLot);

    }

    public static LinkedList queue(PriorityQueue<Product> toysList){
        List<Integer> listWeight = listWeight(toysList);
        List<Integer> listID = listID(toysList);
        LinkedList queue = new LinkedList();
        for (int i = 0; i < listID.size(); i++) {
            int count = listWeight.get(i);
            while (count > 0){
                queue.add(listID.get(i));
                count--;
            }
        }
        return queue;
    }

    public static void get(LinkedList queue, int countLot){
        Integer res = null;
        if (queue.isEmpty()){
            return;
        }
        int lot = lottery(countLot);
        if (queue.contains(lot)){
            res = (int) queue.poll();
            System.out.println(res);
        } else {
            get(queue, countLot);
        }
    }

    public static int lottery(int countLot){
        Random random = new Random();
        int lot = random.nextInt(countLot);
        return lot;
    }

    public static PriorityQueue<Product> put(){
        PriorityQueue<Product> toysQueue = new PriorityQueue<>(Comparator.comparingInt(Product::getWeight));
        while (true) {
            String command = prompt("1 - внести данные\n0 - выйти\nВведите команду: ");
            if (command.equals("0")) {
                return toysQueue;
            }
            switch (command) {
                case "1":
                    Product t = createProduct();
                    toysQueue.add(t);
                    break;
                case "0":
                    break;
            }
        }
    }
    public static String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    public static List<Integer> listID(PriorityQueue<Product> products){
        List<Integer> listID = new ArrayList<>();
        for (Product p: products){
            listID.add(p.getId());
        }
        return listID;
    }

    public static List<Integer> listWeight(PriorityQueue<Product> products){
        List<Integer> listWeight = new ArrayList<>();
        for (Product p: products){
            listWeight.add(p.getWeight());
        }
        return listWeight;
    }

    public static List<String> listName(PriorityQueue<Product> products){
        List<String> listName = new ArrayList<>();
        for (Product p: products){
            listName.add(p.getName());
        }
        return listName;
    }


    public static Product createProduct() {
        int id = Integer.parseInt(prompt("ID: "));
        String name = prompt("Name: ");
        int weight = Integer.parseInt(prompt("Weight: "));
        return new Product(id, name, weight);
    }

    public static String toInput(Product toy) {
        return String.format("%s,%s,%s\n", toy.getId(), toy.getName(), toy.getWeight());
    }


    private static void write(PriorityQueue<Product> products) {
        List<String> lines = new ArrayList<>();
        for (Product toy: products) {
            lines.add(toInput(toy));
        }
        FileOperation.saveAll(lines);
        System.out.println("Данные усешно записаны в файл");
    }

}
