import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindDuplicate {
    public static void main(String[] args) {
        String[] paths = new String[]{
                "root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"};
        String[] paths2 = new String[]{"root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)"};
        System.out.println(findDuplicate(paths));
        System.out.println(findDuplicate(paths2));

    }

    public static List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] pathSplit = path.split(" ");
            String root = pathSplit[0];
            for (int i = 1; i < pathSplit.length; i++) {
                String[] directory = pathSplit[i].split("\\(");
                map.computeIfAbsent(directory[1], x -> new ArrayList<>()).add(root + "/" + directory[0]);
            }
        }
        return map.values().stream().filter(list -> list.size() > 1).collect(Collectors.toList());
    }
}
