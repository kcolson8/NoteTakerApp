# pa7-kcolson8
pa7-kcolson8 created by GitHub Classroom
//I couldn't get the adapter for the spinner to work and I don't know why,
//I've compared this code with others whose code worked and I cant find the issue
// this code would have replaced my existing adapter in NoteActLayout 
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                context,
                android.R.layout.activity_list_item,
                android.R.id.text1,
                spinnerOptions){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
               View view = super.getView(position, convertView, parent);
               String spinnerLabel = spinnerOptions.get(position);
                ImageView imageView = findViewById(android.R.id.icon);
                switch (spinnerLabel){
                    case "Personal":
                        imageView.setImageResource(R.drawable.personal);
                        break;
                    case "School":
                        imageView.setImageResource(R.drawable.school);
                        break;
                    case "Work":
                        imageView.setImageResource(R.drawable.work);
                        break;
                    case "Other":
                        imageView.setImageResource(R.drawable.other);
                        break;
                }
                TextView textView = findViewById(android.R.id.text1);
                textView.setText(spinnerLabel);
               return view;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getDropDownView(position, convertView, parent);
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        label.setAdapter(adapter);
