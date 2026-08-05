class Solution {
    public int maxArea(int[] height) {
             if (height == null || height.length == 0) {
            return 0;
        }
        int result = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int a = Math.min(height[left], height[right]);
            int b = right - left;
            if (a * b > result) {
                result = a * b;
            }
            if (height[left] == a) {
                left++;
            } else {
                right--;
            }
        }

        return result;
    }
}