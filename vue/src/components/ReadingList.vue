<template>
    <div class="book-container">
        <book-card v-bind:book="book" v-for="book in $store.state.books" v-bind:key="book.title" />
    </div>
</template>

<script>
import bookCard from "@/components/BookCard.vue";

export default {
    name: "reading-list",
    components: {
        bookCard,
    },
};
</script>

<style>
.book-container {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}
</style>
