# oop_Ex2
# Assignment of oop_Ex2
https://docs.google.com/document/d/17h5VGIHtqWHrzgoRjH05_PjHgCn8-EDcecrkR9sVChQ/edit
# Directed Weighted Graph
In this project we wrote a code for getting graphs from json file and save them after implementing some functions.

Features of our algorithm :
- Reading graphs from json file.
- Checking if the graph is storngly connected.
- Checking what is the shortest path from first node to last node.
- Giving the shortest path from src node to dest node (with nodes which in the 'road').
- Saving the graph after calculation it with some algorithms to make it more efficient. 
- Checking tsp(with Hemilton Circle).
- Calculation the center of the graph (the most close node to the all other nodes).
- Showing the graph in gui window with some menu options.

In this code we used Dijkstra Algorithm to find if the graph is connected and get the shortest path.

# runnin the code :
for runing the code you can download the jar from out/artifacts/Ex2_jar/Ex2.jar. you will find 4 files - 1 jar and 3 json files.
you need to runing in cmd the code "java -jar Ex2.jar G2.json" (G2.json as an example).
you have 3 json file for example and for sure you can load json files as you want in the same formate.
you can also adding nodes and edges or remove some as you want in the code and use our base for graph like "G1.json" that we loaded as deafult.
for that you can enter in the cmd window the next code  - "java -jar Ex2.jar" and the program will show you a gruph (G1) as deafult.
you can download our projact and enjoy the feautre as we mention before.

our function "shortestPathDist" to calculate the shortest path from source node to another is still on working progress so it not gourenting to work and the all other algorithem use that function to calculate so they also in devlopment level.
that why we didnt add yet a report and a result for running time on big graphs like 10000,100000 and so on....
tzach and yohanan - the devlopment team of the project.
