A simple 'proxy' for some data transformations on top of the SeAT api. Expects an application.yml similar to:

aba:
  api:
    seat:
      apiUrl: http://seat.url.net/api/v1
      apiKey: YOUR_API_KEY_HERE
  producer:
    skills:
      characters:
        - Person 1 You care to check
        - Person 2 You care to check
        - Person N you care to check
      allowedKeys:
        - SOME_KEY_YOU_GIVE_SOMEONE
        - ANOTHER_KEY_YOU_GIVE_SOMEONE_ELSE