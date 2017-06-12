SELECT
  user0_.id       AS id1_1_,
  user0_.age      AS age2_1_,
  user0_.email    AS email3_1_,
  user0_.password AS password4_1_,
  user0_.username AS username5_1_
FROM t_user user0_
WHERE (user0_.username LIKE ?) AND user0_.age > 95
ORDER BY user0_.id ASC