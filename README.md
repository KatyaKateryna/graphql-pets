# GraphQL Pets

## Web Client

Clients can be enabled or disabled in the `application.yml` file.

- [Playground](http://localhost:8080/playground)
- [Graph*i*QL](http://localhost:8080/graphiql)

## Query example

### Value passed directly into the query
```
query {
  cat(id: 1) {
    id
    name
    colors
    __typename
  }
}
```

### Value passed as a variable
```
query Cat ($id: ID!) {
  cat(id: $id) {
    id
    name
    colors
    __typename
  }
}
```
#### Variables:
```
{"id": 1}
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
  }
}
```

### Input passed as a variable

```
mutation CreateCat($cat: CatInput!){
  createCat(cat: $cat) {
    id
    name
    colors
    __typename
  }
}
```
#### Variables:
```
{
  "cat": {
    "name": "Fire",
    "colors": ["RED"]
  }
}
```

## Fragment example

```
query {
  cat(id: 1) {
    ...CatFragment
  }
}

fragment CatFragment on Cat {
  id
  name
  colors
  __typename
}
```