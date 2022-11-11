public class Q1gcdTest{
    public static void main(String[] args)
    {
        int n1 = 2056, n2 = 8818;

		if((n1 == 0) || (n2 == 0)){
			throw new IllegalArgumentException("Cannot enter both as zero");
		}

        int hcf = hcf(n1,n2);

		System.out.println("G.C.D of " + n1 + " and " + n2 + " is " + hcf);

        hcf = hcf(n2,n1);

		System.out.println("G.C.D of " + n2 + " and " + n1 + " is " + hcf);
    }

    public static int hcf(int n1, int n2)
	{
		if(n2 != 0)
		{
            System.out.println("." + n1 + " " + n2);
			return hcf(n2,n1 % n2);
		}
		else
		{
			return n1;
		}
	}
}