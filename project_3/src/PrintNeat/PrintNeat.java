public class PrintNeat {
    static String testString = "Visiting many separate websites frequently to find out if content on the site has been updated can take a long time. Aggregation technology helps to consolidate many websites into one page that can show the new or updated information from many sites. Aggregators reduce the time and effort needed to regularly check websites for updates, creating a unique information space or personal newspaper. Once subscribed to a feed, an aggregator is able to check for new content at user-determined intervals and retrieve the update. The content is sometimes described as being pulled to the subscriber, as opposed to pushed with email or IM. Unlike recipients of some push information, the aggregator user can easily unsubscribe from a feed.\n" +
            "\n" +
            "RSS uses extensible markup language (XML) to structure pieces of information to be aggregated in a feed reader that displays the information in a user-friendly interface.[1] Before subscribing to RSS, users have to install either \"feed reader\" or \"aggregator\" applications in order to read RSS feed. The aggregator provides a consolidated view of the content in one browser display or desktop application. \"Desktop applications offer the advantages of a potentially richer user interface and of being able to provide some content even when the computer is not connected to the Internet. Webbased feed readers offer the great convenience of allowing users to access up-to-date feeds from any Internet-connected computer.\"[2] Although some applications will have an automated process to subscribe to a news feed, the basic way to subscribe is by simply clicking on the RSS icon and/or text link.[2] Aggregation features are frequently built into web portal sites, in the web browsers themselves, in email applications or in application software designed specifically for reading feeds. Aggregators with podcasting capabilities can automatically download media files, such as MP3 recordings. In some cases, these can be automatically loaded onto portable media players (like iPods) when they are connected to the end-user's computer. By 2011, so-called RSS-narrators appeared, which aggregated text-only news feeds, and converted them into audio recordings for offline listening. The syndicated content an aggregator will retrieve and interpret is usually supplied in the form of RSS or other XML-formatted data, such as RDF/XML or Atom.";

    static String[] words = testString.split(" ");

    public static void main(String args[]) {
        int N = words.length + 1;
        int MAX = Integer.MAX_VALUE;
        int M = 45;
        int FreeSpaceLeftAfter[][] = new int[N][N];
        int cost[][] = new int[N][N];
        int[] final_cost = new int[N];
        final_cost[0] = 0;
        int[] splits = new int[N];

        // Calculate the free space left using the words
        for (int i = 1; i <= words.length; i++) {
            FreeSpaceLeftAfter[i][i] = M - words[i - 1].length();
            for (int j = i + 1; j <= words.length; j++) {
                // length per line - number of spaces added due to sepearation of words
                // + summation of all tehe words
                FreeSpaceLeftAfter[i][j] = M - (j - i) - summationOfWords(i, j);
            }
        }

        // Calculate the cost of having extra space in line
        for (int i = 1; i <= words.length; i++) {
            for (int j = i; j <= words.length; j++) {
                if (FreeSpaceLeftAfter[i][j] < 0)
                    // if you cannot fit words in the line then their cost should be maximum
                    cost[i][j] = MAX;
                    // last word, then the free space after the last word should be counted at 0
                else if (j == words.length && FreeSpaceLeftAfter[i][j] >= 0)
                    cost[i][j] = 0;
                else
                    // to find space in each line squarin it, so (3,0,0) == 9 and (1,1,1) == 3
                    cost[i][j] = FreeSpaceLeftAfter[i][j] * FreeSpaceLeftAfter[i][j];
            }
        }


        for (int j = 1; j <= words.length; j++) {
            final_cost[j] = MAX;

            for (int i = 1; i <= j; i++) {
                if (final_cost[i - 1] != MAX && cost[i][j] != MAX &&
                        (final_cost[i - 1] + cost[i][j] < final_cost[j])) {
                    final_cost[j] = final_cost[i - 1] + cost[i][j];
                    splits[j] = i;
                }
            }
        }
        CalulateMinSplit(splits, N - 1);

    }

    private static int summationOfWords(int i, int j) {
        int result = 0;
        for (int k = i; k <= j; k++) {
            result += words[k - 1].length();
        }
        return result;
    }

    static int CalulateMinSplit(int p[], int n) {
        int k;
        if (p[n] == 1)
            k = 1;
        else
            k = CalulateMinSplit(p, p[n] - 1) + 1;
        System.out.println("Line number" + " " + k + ": " +
                "From word no." + " " + p[n] + " " + "to" + " " + n);
        return k;
    }
}
