# GraphQL Pets

## Web Client

Clients can be enabled or disabled in the `application.yml` file.

- [Playground](http://localhost:8080/playground)
- [Graph*i*QL](http://localhost:8080/graphiql)

## Query example

### Value passed directly into the query

```
query {
  cat(id: "00000000-0000-0000-0000-000000000001") {
    id
    name
    colors
    __typename
    passport(fastLoad: true) {
      __typename
      ... on InternalPassport {
        birthDate
      }
      ... on InternationalPassport {
        vaccinationDate
      }
    }
  }
}
```

### Value passed as a variable

```
query Cat ($id: UUID!, $fastLoad: Boolean) {
  cat(id: $id) {
    id
    name
    colors
    __typename
    passport(fastLoad: $fastLoad) {
      __typename
      ... on InternalPassport {
        birthDate
      }
      ... on InternationalPassport {
        vaccinationDate
      }
    }
  }
}
```

#### Variables:

```
{"id": "00000000-0000-0000-0000-000000000001", "fastLoad": true}
```

### Query with fragment

```
query {
  cat(id: "00000000-0000-0000-0000-000000000001") {
    ...CatFragment
  }
}

fragment CatFragment on Cat {
  id
  name
  colors
  __typename
  passport(fastLoad: true) {
    __typename
    ... on InternalPassport {
      birthDate
    }
    ... on InternationalPassport {
      vaccinationDate
    }
  }
}
```

### Query with interface

```
query {
  pet(id: "00000000-0000-0000-0000-000000000000") {
    __typename
    id
    name
    ... on Cat {
      colors
    }
    ... on Dog {
      breed
    }
  }
}
```

### Query with operation directives

```
query Cat($id: UUID!, $withColors: Boolean!, $skipName: Boolean = false) {
  cat(id: $id) {
    id
    name @skip(if: $skipName)
    colors @include(if: $withColors)
  }
}
```

#### Variables:

```
{"id": "00000000-0000-0000-0000-000000000001", "withColors": true}
```

## Mutation example

### Input passed directly into the mutation

```
mutation {
  createCat(cat: {
    name: "Fire",
    colors: [RED]
  }) {
    id
    name
    colors
    __typename
    passport(fastLoad: true) {
      __typename
      ... on InternalPassport {
        birthDate
      }
      ... on InternationalPassport {
        vaccinationDate
      }
    }
  }
}
```

### Input passed as a variable

```
mutation CreateCat($cat: CatInput!, $fastLoad: Boolean){
  createCat(cat: $cat) {
    id
    name
    colors
    __typename
    passport(fastLoad: $fastLoad) {
      __typename
      ... on InternalPassport {
        birthDate
      }
      ... on InternationalPassport {
        vaccinationDate
      }
    }
  }
}
```

#### Variables:

```
{
  "cat": {
    "name": "Fire",
    "colors": ["RED"]
  },
  "fastLoad": true
}
```

## Subscription example

### Subscribe

```
subscription {
  catCount
}
```

### Create a cat

```
mutation {
  createCat(cat: {
    name: "Fire",
    colors: [RED]
  }) {
    id
  }
}
```