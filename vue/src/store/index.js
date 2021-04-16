import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem("token");
const currentUser = JSON.parse(localStorage.getItem("user"));

if (currentToken != null) {
    axios.defaults.headers.common["Authorization"] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
    state: {
        token: currentToken || "",
        user: currentUser || {},
        books: [
            {
                title: "Go Dog Go",
                author: "P. D. Eastman",
                read: false,
                isbn: "0679844902",
                imgLink: "",
                reading: false,
            },
        ],
        total: 0,
    },
    mutations: {
        SET_AUTH_TOKEN(state, token) {
            state.token = token;
            localStorage.setItem("token", token);
            axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
        },

        SET_USER(state, user) {
            state.user = user;
            localStorage.setItem("user", JSON.stringify(user));
        },

        LOGOUT(state) {
            localStorage.removeItem("token");
            localStorage.removeItem("user");
            state.token = "";
            state.user = {};
            delete axios.defaults.headers.common["Authorization"];
        },

        SAVE_BOOK(state, book) {
            state.books.push(book);
        },

        REMOVE_BOOK(state, bookId) {
            let index = state.books.findIndex((thisBook) => thisBook.bookId == bookId);
            state.books.splice(index, 1);
        },

        UPDATE_BOOK_STATUS(state, book) {
            let bookToUpdate = state.books.findIndex((thisBook) => thisBook.bookId == book.bookId);
            Object.assign(bookToUpdate, book);
        },

        SET_MY_BOOKS(state, myBooks) {
            state.books = myBooks;
        },

        SAVE_TIME(state, time) {
            state.total = time;
        },
    },
});
