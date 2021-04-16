/*************************************************************/
/*      RUN EACH QUERY INDIVIDUALLY                          */
/*************************************************************/

INSERT INTO books(isbn, title, author, cover_img_link)
VALUES( -- INSERT VALUES BELOW
        'ISBN'      --isbn_value
        , 'TITLE'    --title_value
        , 'AUTHOR'    --author_value
        , 'IMG'    --cover_img_link_value
);

/*************************************************************/

SELECT book_id FROM books WHERE title = 'TITLE'; -- title_value from above

/*************************************************************/
/*      CHANGE VALUES BELOW                                  */
/*      TO YOUR DATABASE BOOK ID / USER ID                   */
/*************************************************************/

INSERT INTO books_users(book_id, user_id, reading, read)
VALUES( -- INSERT VALUES BELOW
          249      -- book_id from line 9 query 
         , 1     -- user_id
         , false
         , false
 );
 
/*************************************************************/
 
SELECT * FROM books AS b
JOIN books_users AS bu ON b.book_id = bu.book_id
WHERE title = 'TITLE'

 