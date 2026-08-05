class Solution {

    private void dfs(int node,List<List<Integer>> graph,boolean[] sus){
        sus[node] = true;
        for(int nig : graph.get(node)){
            if(!sus[nig]){
                dfs(nig,graph,sus);
            }
        }
    }

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        
        // build adj list

        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }

        for(int[] edge : invocations){
            graph.get(edge[0]).add(edge[1]);
        }

        // finding suspicious methods

        boolean[] sus = new boolean[n];
        dfs(k,graph,sus);


        // checking outside methods for sus

        for(int[] edge : invocations){
            int from = edge[0];
            int to = edge[1];

            if(!sus[from] && sus[to]){  // checking removables
                List<Integer> all = new ArrayList<>();

                for(int i=0;i<n;i++){
                    all.add(i);
                }

                return all;

            }
        }

        // return all non sus as answer

        List<Integer> ans = new ArrayList<>();
        
        for(int i=0;i<n;i++){
            if(!sus[i]){
                ans.add(i);
            }
        }

        return ans;


    }

    
}