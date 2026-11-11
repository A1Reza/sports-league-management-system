# Sports League Management System

A **console-based Java application** built as a **homework/assignment project** for managing **Football**, **Volleyball**, and **Basketball** leagues, plus a collection of **PostgreSQL** practice queries from the same assignment.

The Java part supports adding clubs, recording matches, storing match history, removing clubs, displaying rankings, finding top-scoring clubs, and calculating total points across all leagues. It is built with core OOP concepts: interfaces, abstract classes, inheritance, polymorphism, and plain arrays.

**Author:** Reza Asadipour  
**Type:** Homework / Assignment project  
**Interface:** Console (terminal) — no GUI

---

## Repository Contents

```
SportsLeagueManagementSystem/
├── java/                          # Java application
│   ├── Main.java
│   ├── Match.java
│   ├── Rankable.java
│   ├── Matchable.java
│   ├── Club.java
│   ├── League.java
│   ├── FootballLeague.java
│   ├── VolleyballLeague.java
│   └── BasketballLeague.java
└── sql/                           # PostgreSQL practice
    └── REZA_ASADIPOUR_HW09_Maktab146.sql
```

---

## Part 1 — Java: Sports League Management System (Console-Based)

### Features

- Console menu-driven interface (no GUI)
- Add clubs to Football, Volleyball, and Basketball leagues
- Duplicate club names are rejected (case-insensitive check)
- Maximum **20 clubs** per league
- Record matches and automatically update club statistics
- Store match history — maximum **100 matches** per league
- Remove a club while keeping the club array **compact** (no empty gaps)
- Display rankings sorted by points, then goal difference, using **Bubble Sort**
- Show the **top-scoring club** in each league (returns `null` if the league is empty)
- Calculate the **total points of all clubs** across all leagues
- Display a full ranking table: matches played, wins, draws, losses, goals for, goals against, goal difference, and points

### Scoring Rules

**Football**

| Result | Points |
|---|---|
| Win | 3 |
| Draw | 1 |
| Loss | 0 |

**Volleyball**

| Result | Points |
|---|---|
| Win by 2 or more sets (3–0, 3–1) | 3 |
| Win by 1 set (3–2) | 2 |
| Loss by 1 set (2–3) | 1 |
| Any other loss | 0 |

**Basketball**

| Result | Points |
|---|---|
| Win | 2 |
| Loss | 0 |
| Bonus: winning margin **greater than 20** | +1 |

### OOP Design

- `Rankable` → `getPoints()`, `getGoalDifference()`
- `Matchable` → `recordMatch(...)`
- `Club` implements `Rankable`
- `League` (abstract) implements `Matchable`
  - Abstract: `calculatePoints(...)`, `addClub(...)`, `findClub(...)`
  - Shared helpers: `addToLeague(...)`, `addToMatch(...)`, `sortClubs()`, `displayRankings()`, `displayMatchHistory()`, `removeClub(...)`, `getTopScoringClub()`
- `FootballLeague`, `VolleyballLeague`, `BasketballLeague` extend `League` with their own scoring rules
- `Match` stores match details
- `Main` provides the console menu UI

### Menu

```
------ Sports League Management System Menu ------
1.  Add Club to Football League
2.  Add Club to Volleyball League
3.  Add Club to Basketball League

4.  Record Football Match
5.  Record Volleyball Match
6.  Record Basketball Match

7.  Remove Club from League

8.  Display All League Rankings
9.  Show Match History
10. Show Top Scoring Club per League
11. Show Total Points of All Clubs

12. Exit
```

### How to Run

```bash
javac *.java
java Main
```

### Constraints

- Console-based only (no GUI)
- Plain arrays only — no `ArrayList`
- Bubble Sort inside `sortClubs()`
- Max **20 clubs** and **100 matches** per league
- Clear success and error messages for every operation

---

## Part 2 — PostgreSQL Practice Queries (Assignment)

A set of **20 SQL exercises** on the classic `employees` sample database (tables: `employees`, `salaries`, `departments`, `dept_manager`, `titles`).

### Concepts Covered

- String concatenation (`CONCAT`, `||`)
- String functions (`LENGTH`, `LIKE`, `NOT LIKE`, `%pattern%`)
- Aggregation (`COUNT`, `SUM`, `AVG`, `MIN`, `MAX`)
- `BETWEEN`, `IN`, `IS NULL`, `IS NOT NULL`
- `COALESCE` for NULL handling
- Operator precedence with `AND` / `OR`
- Date filtering with `BETWEEN` and comparisons
- Date formatting with `TO_CHAR(...)` (`'Month YYYY'`, `'Day, "Year" YYYY'`)
- Age calculation with `AGE(...)` and `EXTRACT(YEAR FROM ...)`
- `DISTINCT`, `ORDER BY` (ASC / DESC)

### Sample Query

```sql
-- Q10: employees with Engineer/Staff titles from 1985–1992
SELECT emp_no,
       title,
       CONCAT(title, ' from ', from_date) AS TITLE_PERIOD
FROM titles
WHERE from_date BETWEEN '1985-01-01' AND '1992-12-31'
  AND (title LIKE '%Engineer%' OR title LIKE '%Staff%')
  AND (to_date IS NULL OR to_date = '9999-01-01');
```

All 20 queries are in `sql/REZA_ASADIPOUR_HW09_Maktab146.sql`, each preceded by its question as a comment.
