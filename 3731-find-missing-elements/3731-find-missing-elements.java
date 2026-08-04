class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = nums[0];
        int max = nums[0];

        for(int num : nums){
            min = Math.min(min,num);
            max = Math.max(max,num);
        }

        HashSet<Integer> s = new HashSet<>();

        for(int num : nums){
            s.add(num);
        }

        List<Integer> result = new ArrayList<>();

        for(int i=min;i<max;i++){
            if(!s.contains(i)){
                result.add(i);
            }
        }

        return result;
    }
}