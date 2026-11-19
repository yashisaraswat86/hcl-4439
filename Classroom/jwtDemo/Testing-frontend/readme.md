#Testing
1. Unit testing -> isolation
2. Integrated Testing
3. End to End Testing

NodeJs by default support in built testing framework from node=>23 version

create simple node app in node-test folder
npm init -y

create app.js in node-test

```
export const app = (name) =>{
    return `Hello, ${name}`
};
```

export here signify that the method should be used by other file
