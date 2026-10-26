import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st0 = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st0.nextToken());
        int n = Integer.parseInt(st0.nextToken());
        List<int[]> free = new ArrayList<int[]>();
        free.add(new int[]{1, m});
        Map<Integer, int[]> blocks = new HashMap<Integer, int[]>();
        int nextId = 1;
        StringBuilder out = new StringBuilder();
        for (int q = 0; q < n; q++) {
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line);
            String cmd = st.nextToken();
            if (cmd.equals("alloc")) {
                int need = Integer.parseInt(st.nextToken());
                boolean placed = false;
                for (int i = 0; i < free.size(); i++) {
                    int[] f = free.get(i);
                    if (f[1] >= need) {
                        blocks.put(nextId, new int[]{f[0], need});
                        if (f[1] == need) free.remove(i);
                        else free.set(i, new int[]{f[0]+need, f[1]-need});
                        out.append(nextId).append("\n");
                        nextId++;
                        placed = true;
                        break;
                    }
                }
                if (!placed) { out.append("NULL\n"); nextId++; }
            } else if (cmd.equals("erase")) {
                int x = Integer.parseInt(st.nextToken());
                if (blocks.containsKey(x)) {
                    int[] b = blocks.remove(x);
                    free.add(b);
                    free.sort((a,c) -> a[0]-c[0]);
                    List<int[]> merged = new ArrayList<int[]>();
                    for (int[] seg : free) {
                        if (!merged.isEmpty() && merged.get(merged.size()-1)[0]+merged.get(merged.size()-1)[1] == seg[0]) {
                            merged.get(merged.size()-1)[1] += seg[1];
                        } else merged.add(seg);
                    }
                    free = merged;
                } else {
                    out.append("ILLEGAL_ERASE_ARGUMENT\n");
                }
            } else {
                List<Map.Entry<Integer,int[]>> occ = new ArrayList<Map.Entry<Integer,int[]>>(blocks.entrySet());
                occ.sort((a,b) -> a.getValue()[0]-b.getValue()[0]);
                int pos = 1;
                Map<Integer,int[]> newBlocks = new HashMap<Integer,int[]>();
                for (Map.Entry<Integer,int[]> e : occ) {
                    int l = e.getValue()[1];
                    newBlocks.put(e.getKey(), new int[]{pos, l});
                    pos += l;
                }
                blocks = newBlocks;
                free = new ArrayList<int[]>();
                if (pos <= m) free.add(new int[]{pos, m-pos+1});
            }
        }
        System.out.print(out);
    }
}