<template>
    <div class="center" v-bind:class="{ isbn: book.isbn }">
        <h2 class="book-title">{{ book.title }}</h2>
        <h3 class="book-author">By: {{ book.author }}</h3>
        <h3 class="book-isbn">ISBN: {{ book.isbn }}</h3>
        <img v-if="book.isbn" v-bind:src="'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-M.jpg'" />
        <img v-if="!book.isbn" v-bind:src="'https://mrb.imgix.net/assets/default-book.png'" />
        <p></p>
        <router-link v-bind:to="{ name: 'my-books' }">Return to Reading List</router-link>
    </div>
</template>
<script>
export default {
    name: "book-details",
    data() {
        return {
            title: "",
            author: "",
            isbn: "",
        };
    },
    created() {
        const title = this.$route.params.title;
        this.book = this.$store.state.books.find((book) => book.title == title);
    },
};
</script>
<style></style>
